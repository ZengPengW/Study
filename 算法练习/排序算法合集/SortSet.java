package com.reflectTest.www;

import java.util.ArrayList;
import java.util.Collections;

public class SortSet {

	public static void shuffle(int[] arr) {
		ArrayList<Integer> ls = new ArrayList<Integer>();
		for (int intt : arr) {
			ls.add(intt);
		}
		Collections.shuffle(ls);

		for (int i = 0; i < arr.length; i++) {
			arr[i] = ls.get(i);
		}
	}

	public static void print(int[] arr, String s) {
		System.out.print(s + ": ");
		for (int i : arr) {
			System.out.print(i);
		}
		System.out.println();
	}

	public static void main(String[] args) throws Exception {

		int[] arr = { 0, 2, 7, 9, 5, 4, 6, 3, 1, 8 };
		xierSort(arr);
		print(arr, "希尔排序");

		shuffle(arr);
		bubbleSort(arr);
		print(arr, "冒泡排序");

		shuffle(arr);
		selectSort(arr);
		print(arr, "选择排序");

		shuffle(arr);
		fast(arr, 0, arr.length - 1);
		print(arr, "快速排序");

		shuffle(arr);
		threefast(arr, 0, arr.length - 1);
		print(arr, "三分快速排序");

		shuffle(arr);
		mergerdfs(arr, 0, arr.length - 1);
		print(arr, "归并排序");

		shuffle(arr);
		posmerger(arr);
		print(arr, "正向归并排序");

		shuffle(arr);
		insertSort(arr);
		print(arr, "插入排序");

		int[] arr1 = { 0, 2, 7, 9, 5, 4, 6, 3, 1, 8 };
		sinkSort(arr1);
		print(arr1, "队列优先(堆)排序");
	}

	// 选择排序
	public static void selectSort(int[] arr) {
		int min = 0;
		for (int i = 0; i < arr.length; i++) {
			min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}

			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
		}

	}

	// 插入排序
	public static void insertSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}

			}

		}
	}

	// 希尔排序
	public static void xierSort(int[] arr) {
		int id = 1;
		while ((arr.length) / 3 > id)
			id = id * 3 + 1;

		while (id >= 1) {
			for (int i = id; i < arr.length; i++) {
				for (int j = i; j >= id; j -= id) {
					if (arr[j] < arr[j - id]) {
						int temp = arr[j];
						arr[j] = arr[j - id];
						arr[j - id] = temp;
					}
				}
			}
			id /= 3;
		}
	}

	// 冒泡排序
	public static void bubbleSort(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}

			}

		}
	}

	// 快速排序切分
	public static int cut(int[] arr, int lo, int hi) {
		int temp = arr[lo];

		while (lo < hi) {
			while (arr[hi] > temp && lo < hi)
				hi--;
			arr[lo] = arr[hi];

			while (arr[lo] < temp && lo < hi)
				lo++;
			arr[hi] = arr[lo];

		}
		arr[hi] = temp;
		return hi;

	}

	// 快速排序
	public static void fast(int[] arr, int lo, int hi) {
		if (lo >= hi) {
			return;
		}

		int index = cut(arr, lo, hi);
		fast(arr, lo, index - 1);
		fast(arr, index + 1, hi);

	}

	// 归并操作
	private static int[] a;

	public static void merger(int[] arr, int lo, int mid, int hi) {
		if (arr[mid] < arr[mid + 1]) {
			return;
		}
		int j = lo;
		int k = mid + 1;
		a = new int[hi + 1];
		for (int i = lo; i <= hi; i++) {
			a[i] = arr[i];
		}
		for (int i = lo; i <= hi; i++) {
			if (j > mid)
				arr[i] = a[k++];
			else if (k > hi)
				arr[i] = a[j++];
			else if (a[j] < a[k])
				arr[i] = a[j++];
			else
				arr[i] = a[k++];
		}

	}

	// 归并
	public static void mergerdfs(int[] arr, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int mid = (hi - lo) / 2 + lo;
		mergerdfs(arr, lo, mid);
		mergerdfs(arr, mid + 1, hi);
		merger(arr, lo, mid, hi);
	}

	// 正向归并
	public static void posmerger(int[] arr) {
		int n = arr.length;
		for (int i = 1; i < n; i += i) {
			for (int j = 0; j < n - i; j += i + i) {
				merger(arr, j, j + i - 1, Math.min(n - 1, j + i - 1 + i));
			}

		}

	}

	// 三分快速排序
	public static void threefast(int[] arr, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int lt = lo, i = lo + 1, gt = hi;
		while (i <= gt) {
			if (arr[i] < arr[lt]) {
				int temp = arr[i];
				arr[i] = arr[lt];
				arr[lt] = temp;
				i++;
				lt++;
			} else if (arr[i] > arr[lt]) {
				int temp = arr[i];
				arr[i] = arr[gt];
				arr[gt] = temp;
				gt--;
			} else {
				i++;
			}

		}
		threefast(arr, lo, lt - 1);
		threefast(arr, gt + 1, hi);
	}

	// 下沉构建堆
	public static void sink(int[] arr, int n, int len) {
		int j = n * 2;
		while (j <= len) {
			if (j + 1 <= len && arr[j] < arr[j + 1])
				j++;
			if (arr[j] > arr[n]) {
				int temp = arr[j];
				arr[j] = arr[n];
				arr[n] = temp;

			} else {
				break;
			}
			n = j;
			j = n * 2;
		}
	}

	// 上浮构建堆
	public static void rise(int[] arr, int n, int len) {
		int j = n / 2;
		while (j >= 1) {
			if (arr[j] < arr[n]) {
				int temp = arr[j];
				arr[j] = arr[n];
				arr[n] = temp;
			} else {
				break;
			}
			n = j;
			j = n / 2;
		}
	}

	// 队列优先排序
	public static void sinkSort(int[] arr) {
		int len = arr.length - 1;
		// for(int i=len/2;i>=1;i--)sink(arr,i,len);//下构建堆
		for (int i = 2; i <= len; i++)
			rise(arr, i, len);// 上浮构建堆
		while (len > 1) {
			int temp = arr[len];
			arr[len] = arr[1];
			arr[1] = temp;
			len--;
			sink(arr, 1, len);
		}

	}
}
