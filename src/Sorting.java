import java.util.Arrays;

/**
 * Created by Andreas Brommund on 15-12-21.
 */
public class Sorting {
    public static void main(String[] args) {


        int[][] sort = {
                {},
                {1},
                {1,2},
                {2,1},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {6, 5, 3, 1, 8, 7, 2, 4},
                {1, 2, 3, 4, 5, 6, 7, 8},
                {8, 7, 6, 5, 4, 3, 2, 1},
                {404,62,65,94,11,11,5,19,88,78,48,51,27,32,14,74,53,3,9,94,38,98,62,99,79,2,7,73,69,41,53,85,87,29,39,2,59,69,58,82,6,25,8,3,57,91,85,78,37,2,27,7,41,44,4,42,77,59,13,25,66,34,82,85,31,76,96,46,75,48,6,12,31,35,8,13,36,3,44,25,26,5,56,81,1,64,92,49,23,96,63,76,57,81,97,67,11,8,23,1,62,65,94,11,11,5,19,88,78,48,51,27,32,14,74,53,3,9,94,38,98,62,99,79,2,7,73,69,41,53,85,87,29,39,2,59,69,58,82,6,25,8,3,57,91,85,78,37,2,27,7,41,44,4,42,77,59,13,25,66,34,82,85,31,76,96,46,75,48,6,12,31,35,8,13,36,3,44,25,26,5,56,81,1,64,92,49,23,96,63,76,57,81,97,67,11,8,23,1}
        };


        for (int i = 0;i < 1000000 ; i++){
            sort(sort,"BubbleSort", false,1);
        }

        sort(sort, "BubbleSort", true,1000000);

        for (int i = 0;i < 1000000 ; i++){
            sort(sort,"SelectionSort", false,1);
        }

        sort(sort, "SelectionSort", true, 1000000);

        for (int i = 0;i < 1000000 ; i++){
            sort(sort,"InsertionSort", false,1);
        }

        sort(sort, "InsertionSort", true, 1000000);

        for (int i = 0;i < 1000000 ; i++){
            sort(sort,"QuickSort", false,1);
        }

        sort(sort, "QuickSort", true, 1000000);

    }

    /**
     *
     * @param list the list
     * @param lo (inclusive)
     * @param hi (exclusive)
     */
    public static void quickSort(int[] list,int lo, int hi){
        if (hi <= lo){ //Can use insertion sort if the length of the sub list is less than a specific value.
            return;
        }
        int pivot = list[lo]; //Can improve

        int[] index = partition(list,lo,hi,pivot);
        quickSort(list,lo,index[0]);
        quickSort(list,index[1],hi);
    }

    /**
     *
     * left is the start index of the array (inclusive)
     * right is the end index of the array (exclusive)
     * pivot holds the value of the elements that should be in the middle section
     *
     * e.g. [1 2 | 3 3 3 | 7 9 9]
     * Return a int array with length 2.
     * The value with index 0 holds the index to the first element in the middle section (e.g = 2).
     * The value with index 1 holds the index to the first element in the third section (e.g = 5).
     *
     */
    public static int[] partition(int[] list, int left, int right, int pivot){
        int i = left;
        int j = left;    //The element we check right now
        int n = right-1; //The "end" of the array, all elements behind n are bigger than the pivot.

        while (j <= n){
            if (list[j] < pivot){
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                i++;
                j++;
            }else if (list[j] > pivot){
                int temp = list[n];
                list[n] = list[j];
                list[j] = temp;
                n--;
            }else{
                j++;
            }
        }
        int[] index = {i,j};
        return index;
    }

    public static void insertionSort(int[] list){
        int length = list.length;
        for (int i = 1; i < length;i++) {
            int value = list[i];
            int j = i;
            while (j > 0 && value < list[j-1]) {
                list[j] = list[j-1];
                j--;
            }
            list[j] = value;
        }
    }

    public static void selectionSort(int[] list){
        int length = list.length;
        for(int i = 0;i < length;i++){
            int minIndex = i;
            int minValue = list[i];
            for(int j = i+1;j < length;j++){
                if(list[j] < minValue){
                    minValue = list[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                int temp = list[minIndex];
                list[minIndex] = list[i];
                list[i] = temp;
            }
        }
    }

    public static void bubbleSort(int[] list){
        int length = list.length;
        boolean sorted;
        int n = 1;
        do{
            sorted = true;
            for(int i = 0;i < length-n;i++){
                if(list[i] > list[i+1]){
                    sorted = false;
                    int temp = list[i+1];
                    list[i+1] = list[i];
                    list[i] = temp;
                }
            }
            n++;
        }while(!sorted);
    }

    public static void sort(int[][] sort,String sorting,boolean print,int times){
        if (print) {
            System.out.print(sorting);
            System.out.println("----------------------------");
            System.out.println("Before\tAfter\tSorted\tTime\t");
        }
        for(int i = 0;i < sort.length;i++) {

            long start;
            long end;
            long tot = 0;

            int[] list = Arrays.copyOf(sort[i], sort[i].length);
            int[] sorted = Arrays.copyOf(sort[i], sort[i].length);
            Arrays.sort(sorted);
            if (print) {
                System.out.print(Arrays.toString(list));
            }
            int[] l = new int[list.length];
            for (int j = 0; j < times; j++) {
                l = Arrays.copyOf(list, list.length);
                switch (sorting) {
                    case "BubbleSort":

                        start = System.nanoTime();
                        bubbleSort(l);
                        end = System.nanoTime();
                        tot += (end - start);

                        break;

                    case "SelectionSort":

                        start = System.nanoTime();
                        selectionSort(l);
                        end = System.nanoTime();
                        tot += (end - start);
                        break;

                    case "InsertionSort":

                        start = System.nanoTime();
                        insertionSort(l);
                        end = System.nanoTime();
                        tot += (end - start);
                        break;
                    case "QuickSort":

                        start = System.nanoTime();
                        quickSort(l,0,l.length);
                        end = System.nanoTime();
                        tot += (end - start);
                        break;
                }


            }
            if(print){
                System.out.print("\t"+Arrays.toString(l));
                System.out.print("\t"+Arrays.equals(sorted, l));
                System.out.print("\t" + (tot/times) + "\n");
            }
        }
    }
}
