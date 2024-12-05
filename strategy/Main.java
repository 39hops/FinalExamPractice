package strategy;

class SortingContext {
    private SortingStrategy sortingStrategy;

    SortingContext(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    void performSort(int[] arr) {
        sortingStrategy.sort(arr);
    }
}

interface SortingStrategy {
    void sort(int[] arr);
}

class BubbleSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        int i, j, temp;
        boolean swapped = false;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
    }
}

class MergeSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        sort(arr, l, r);
    }

    private void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int arr[], int l, int m, int r) {
        int sizeOne = m - l + 1;
        int sizeTwo = r - m;
        int[] left = new int[sizeOne];
        int[] right = new int[sizeTwo];
        for (int i = 0; i < sizeOne; ++i)
            left[i] = arr[l + i];
        for (int j = 0; j < sizeTwo; j++)
            right[j] = arr[m + 1 + j];
        int i = 0, j = 0;
        int k = l;
        while (i < sizeOne && j < sizeTwo) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < sizeOne) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < sizeTwo) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
}

class QuickSortStrategy implements SortingStrategy {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        quickSort(arr, 0, n - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int part = partition(arr, low, high);
            quickSort(arr, low, part - 1);
            quickSort(arr, part + 1, high);
        }
    }

    private int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class Main {
    public static void main(String[] args) {
        /**
         * Can swap out "new MergeSortStrategy()" with whatever you want.
         */
        SortingContext sortingContext = new SortingContext(new MergeSortStrategy());

        int[] arr = { 5, 6, 8, 9, 2, 32, 23, 6 };
        sortingContext.performSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}