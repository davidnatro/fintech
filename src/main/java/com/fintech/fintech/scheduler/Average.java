package com.fintech.fintech.scheduler;

public class Average {

  private final int windowSize;
  private final double[] values;
  private double sum;
  private int size;

  public Average(int windowSize) {
    this.windowSize = windowSize;
    this.values = new double[windowSize];
    this.sum = 0;
    this.size = 0;
  }

  public double calculate(double value) {
    if (size < windowSize) {
      values[size] = value;
      sum += value;
      size += 1;
    } else {
      int index = size % windowSize;
      sum -= values[index];
      values[index] = value;
      sum += value;
    }

    return sum / size;
  }
}
