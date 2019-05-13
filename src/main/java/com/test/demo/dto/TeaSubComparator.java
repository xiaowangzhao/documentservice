package com.test.demo.dto;

import com.test.demo.entity.TeaSubSum;

import java.util.Comparator;

/**按照题目数升序排序*/
public class TeaSubComparator implements Comparator<TeaSubSum> {
    public int compare(TeaSubSum o1, TeaSubSum o2){
        int subsum1=o1.getSubsum();
        int subsum2=o2.getSubsum();
        return subsum2-subsum1;
    }
}
