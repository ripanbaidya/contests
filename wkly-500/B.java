class Solution {
  public int sumOfPrimesInRange(int n) {
    int r = reverse(n);

    int low = Math.min(n, r);
    int high = Math.max(n, r);

    int sum = 0;

    for (int i = low; i <= high; i++) {
      if (isPrime(i))
        sum += i;
    }

    return sum;
  }

  private int reverse(int n) {
    int rev = 0;
    while (n != 0) {
      rev = rev * 10 + (n % 10);
      n /= 10;
    }

    return rev;
  }

  private boolean isPrime(int num) {
    if (num < 2)
      return false;
    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0)
        return false;
    }

    return true;
  }
}