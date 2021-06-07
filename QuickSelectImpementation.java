//quick select

Class QuickSelectImplementation{
	private int partition(int[] arr, int leftPointer, int right, int pivot) {
		int left= leftPointer;

		for(int i = left; i < right; i++) {
			if(arr[i] < pivot) {
				swap(arr, left, i);
				left++;
			}
		}

		//?为什么swap right?
		swap(arr, right, left);
		return left;
	}

	private void swap(int[] input, int a, int b) {
		int tmp = input[a];
		input[a] = input[b];
		input[b] = tmp;
	}
}