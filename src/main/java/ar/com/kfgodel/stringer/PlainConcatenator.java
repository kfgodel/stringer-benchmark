package ar.com.kfgodel.stringer;

import ar.com.kfgodel.stringer.impl.builder.MutableBuilder;
import com.google.common.base.Joiner;

import java.util.StringJoiner;

/**
 * Date: 17/12/18 - 12:31
 */
public class PlainConcatenator {

  public String formatWithCompiler(String arg1, String arg2, String arg3) {
    return "This is a message with 3 args: [" + arg1 + ", " + arg2 + ", " + arg3 + "]";
  }

  public String formatWithStringFormatMethod(String arg1, String arg2, String arg3) {
    return String.format("This is a message with 3 args: [%s, %s, %s]", arg1, arg2, arg3);
  }

  public String formatWithGuavaJoiner(String arg1, String arg2, String arg3) {
    return Joiner.on("").join(
      "This is a message with 3 args: [",
      arg1,
      ", ",
      arg2,
      ", ",
      arg3,
      "]"
    ).toString();
  }

  public String formatWithStringJoiner(String arg1, String arg2, String arg3) {
    return new StringJoiner("This is a message with 3 args: [")
      .add(arg1)
      .add(", ")
      .add(arg2)
      .add(", ")
      .add(arg3)
      .add("]")
      .toString();
  }

  public String formatWithStringer(String arg1, String arg2, String arg3){
    return MutableBuilder.createDefault()
      .with("This is a message with 3 args: [")
      .with(arg1)
      .with(", ")
      .with(arg2)
      .with(", ")
      .with(arg3)
      .with("]")
      .toString();
  }

  public static PlainConcatenator create() {
    PlainConcatenator concatenator = new PlainConcatenator();
    return concatenator;
  }

}
