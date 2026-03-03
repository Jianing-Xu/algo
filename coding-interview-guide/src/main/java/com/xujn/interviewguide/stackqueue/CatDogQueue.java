package com.xujn.interviewguide.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * 猫狗队列。
 */
public class CatDogQueue {

    private final Deque<PetEnterQueue> dogQueue = new ArrayDeque<>();
    private final Deque<PetEnterQueue> catQueue = new ArrayDeque<>();
    private long sequence = 0;

    public void add(Pet pet) {
        PetEnterQueue entry = new PetEnterQueue(pet, sequence++);
        if ("dog".equals(pet.getType())) {
            dogQueue.offer(entry);
            return;
        }
        if ("cat".equals(pet.getType())) {
            catQueue.offer(entry);
            return;
        }
        throw new IllegalArgumentException("pet type must be cat or dog");
    }

    public Pet pollAll() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        if (dogQueue.isEmpty()) {
            return catQueue.poll().pet();
        }
        if (catQueue.isEmpty()) {
            return dogQueue.poll().pet();
        }
        return dogQueue.peek().sequence() < catQueue.peek().sequence()
                ? dogQueue.poll().pet()
                : catQueue.poll().pet();
    }

    public Dog pollDog() {
        if (dogQueue.isEmpty()) {
            throw new NoSuchElementException("dog queue is empty");
        }
        return (Dog) dogQueue.poll().pet();
    }

    public Cat pollCat() {
        if (catQueue.isEmpty()) {
            throw new NoSuchElementException("cat queue is empty");
        }
        return (Cat) catQueue.poll().pet();
    }

    public boolean isEmpty() {
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }

    public abstract static class Pet {
        private final String type;

        protected Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    private record PetEnterQueue(Pet pet, long sequence) {
    }
}
