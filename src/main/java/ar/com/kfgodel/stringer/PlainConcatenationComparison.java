package ar.com.kfgodel.stringer;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class PlainConcatenationComparison {


  private PlainConcatenator concatenator = PlainConcatenator.create();
  private String arg1 = "Hola";
  private String arg2 = "Mundo";
  private String arg3 = "!";

  @Benchmark
  public void noOpZero() {
    // This is a reference test so we know what the best case is on this machine
  }

  @Benchmark
  public String formatWithCompiler() {
    return concatenator.formatWithCompiler(arg1, arg2, arg3);
  }

  @Benchmark
  public String formatWithGuavaJoiner() {
    return concatenator.formatWithGuavaJoiner(arg1, arg2, arg3);
  }
  @Benchmark
  public String formatWithStringer() {
    return concatenator.formatWithStringer(arg1, arg2, arg3);
  }
  @Benchmark
  public String formatWithStringFormatMethod() {
    return concatenator.formatWithStringFormatMethod(arg1, arg2, arg3);
  }
  @Benchmark
  public String formatWithStringJoiner() {
    return concatenator.formatWithStringJoiner(arg1, arg2, arg3);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(PlainConcatenationComparison.class.getSimpleName())
      .warmupIterations(10)
      .measurementIterations(20)
      .timeUnit(TimeUnit.MICROSECONDS)
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}