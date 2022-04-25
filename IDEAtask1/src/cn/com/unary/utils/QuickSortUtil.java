package cn.com.unary.utils;

//快速排序算法
public class QuickSortUtil
{

    private void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void quickSort(int[] arr, int start, int end)
    {
        if (start >= end)
            return;
        int k = arr[start];
        int i = start, j = end;
        while (i != j)
        {
            while (i < j && arr[j] >= k)
                --j;
            swap(arr, i, j);
            while (i < j && arr[i] <= k)
                ++i;
            swap(arr, i, j);
        }
        quickSort(arr, start, i - 1);
        quickSort(arr, i + 1, end);
    }

}

