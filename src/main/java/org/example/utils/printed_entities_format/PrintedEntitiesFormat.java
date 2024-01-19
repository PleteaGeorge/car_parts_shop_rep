package org.example.utils.printed_entities_format;

public class PrintedEntitiesFormat {
  public static final String END_LINE = "\n";

  public static String getIndent(int numberOfSpacesToIndent) {
    StringBuilder result = new StringBuilder();
    while (0 < numberOfSpacesToIndent) {
      result.append(" ");
      --numberOfSpacesToIndent;
    }
    return result.toString();
  }
}
