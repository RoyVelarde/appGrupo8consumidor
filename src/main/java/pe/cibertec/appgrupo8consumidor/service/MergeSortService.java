package pe.cibertec.appgrupo8consumidor.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MergeSortService {
    public Integer[] sort(Integer[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        Integer[] left = Arrays.copyOfRange(array, 0, mid);
        Integer[] right = Arrays.copyOfRange(array, mid, array.length);
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    private Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] merged = new Integer[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }
        while (i < left.length) {
            merged[k++] = left[i++];
        }
        while (j < right.length) {
            merged[k++] = right[j++];
        }
        return merged;
    }
}
