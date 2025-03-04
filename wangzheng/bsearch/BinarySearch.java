package bsearch;

public class BinarySearch {

    public static int binarySearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchInternally(int[] arr, int left, int right, int value) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] < value) {
            return binarySearchInternally(arr, mid + 1, right, value);
        } else {
            return binarySearchInternally(arr, left, mid - 1, value);
        }
    }

    public static int binarySearchFirstIndex(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == value) {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchLastIndex(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == value) {
                if (mid == arr.length - 1 || arr[mid + 1] != value) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchFirstGreater(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= value) {
                if (mid == 0 || arr[mid - 1] < value) return mid;
                else right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearchLastLess(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= value) {
                if (mid == arr.length - 1 || arr[mid + 1] > value) return mid;
                else left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 5, 7, 9};
        System.out.println(binarySearch(arr, 5));

        int[] arr2 = new int[]{1, 2, 3, 3, 3, 5, 7, 9};
        System.out.println(binarySearchFirstIndex(arr2, 3));
        System.out.println(binarySearchLastIndex(arr2, 3));
        System.out.println(binarySearchFirstGreater(arr2, 4));
        System.out.println(binarySearchLastLess(arr2, 4));
    }
}
