import java.io.IOException;

class SayNumber {
  private static final String[] ones = {
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  private static final String[] teens = {
    "ten",
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen"
  };
  private static final String[] tens = {
    "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
  };

  public static String convertToWords(Long num) {
    if (num < 1 || num > 999999999999L) {
      return "Number out of range. Please enter a number between 1 and 999,999,999,999.";
    }

    if (num < 10) {
      return ones[Math.toIntExact(num)];
    } else if (num < 20) {
      return teens[(int) (num - 10)];
    } else if (num < 100) {
      return tens[(int) (num / 10)] + (num % 10 == 0 ? "" : " " + ones[(int) (num % 10)]);
    } else if (num < 1000) {
      return ones[(int) (num / 100)]
          + " hundred"
          + (num % 100 == 0 ? "" : " " + convertToWords(num % 100));
    } else if (num < 1000000) {
      return convertToWords(num / 1000)
          + " thousand"
          + (num % 1000 == 0 ? "" : " " + convertToWords(num % 1000));
    } else if (num < 1000000000) {
      return convertToWords(num / 1000000)
          + " million"
          + (num % 1000000 == 0 ? "" : " " + convertToWords(num % 1000000));
    } else {
      return convertToWords(num / 1000000000)
          + " billion"
          + (num % 1000000000 == 0 ? "" : " " + convertToWords(num % 1000000000));
    }
  }

  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      throw new IllegalArgumentException("Please provide a number as an argument.");
    }

    StringBuilder sb = new StringBuilder();
    for (String arg : args) {
      sb.append(arg);
    }
    String givenNum = sb.toString().replace(" ", "");
    Long num = Long.parseLong(givenNum);

    char[] arr = givenNum.toCharArray();
    for (char ch : arr) {
      if (!Character.isDigit(ch)) {
        throw new IllegalArgumentException("Please provide a digit only.");
      }
    }

    String result = convertToWords(num);
    System.out.println(result);

    String[] command = {"C:\\Program Files\\eSpeak NG\\espeak-ng.exe", result};
    Runtime.getRuntime().exec(command);
  }
}
