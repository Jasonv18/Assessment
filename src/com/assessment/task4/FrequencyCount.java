package com.assessment.task4;

/**
 * Created by A221824 on 2018/04/05.
 */
public class FrequencyCount {

  public FrequencyCount() {

  }

  public Integer findCounts(int arraytest[], int n, int k) {
    for (int i = 0; i < n; i++) {
      arraytest[(arraytest[i] % k)] += k;
    }

    int max = arraytest[0], maxCount = 0;
    for (int i = 1; i < n; i++) {
      if (arraytest[i] > max) {
        max = arraytest[i];
        maxCount = i;
      }
    }

    return maxCount;
  }
}

