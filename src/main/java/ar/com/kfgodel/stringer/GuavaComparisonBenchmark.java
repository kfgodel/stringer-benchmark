package ar.com.kfgodel.stringer;

import ar.com.kfgodel.stringer.api.Stringer;
import ar.com.kfgodel.stringer.impl.builder.MutableBuilder;
import com.google.common.base.MoreObjects;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class GuavaComparisonBenchmark {


  private ExamplePojo pojo = ExamplePojo.createDefault();
  private Stringer stringer;

  @Setup(Level.Trial)
  public void prepareStringers() {
    stringer = createStringer();
  }

  private Stringer createStringer() {
    return MutableBuilder.createDefault()
      .with(pojo.getClass().getSimpleName())
      .enclosingAsState((builder) ->
        builder
          .withProperty(ExamplePojo.name_FIELD, pojo::getName)
          .andProperty(ExamplePojo.age_FIELD, pojo::getAge)
          .andProperty(ExamplePojo.id_FIELD, pojo::getId)
          .andProperty(ExamplePojo.telephone_FIELD, pojo::getTelephone)
      )
      .build();
  }


  @Benchmark
  public void noOpZero() {
    // This is a reference test so we know what the best case is on this machine
  }

  @Benchmark
  public String renderAnExamplePojoWithGuava() {
    return MoreObjects.toStringHelper(pojo)
      .add(ExamplePojo.name_FIELD, pojo.getName())
      .add(ExamplePojo.age_FIELD, pojo.getAge())
      .add(ExamplePojo.id_FIELD, pojo.getId())
      .add(ExamplePojo.telephone_FIELD, pojo.getTelephone())
      .toString();
  }

  @Benchmark
  public String renderAnExamplePojoWithAStringerBuilder() {
    return createStringer()
      .get();
  }

  @Benchmark
  public String renderAnExamplePojoWithAStringer() {
    return stringer.get();
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
      .include(GuavaComparisonBenchmark.class.getSimpleName())
      .warmupIterations(5)
      .measurementIterations(5)
      .timeUnit(TimeUnit.MICROSECONDS)
      .forks(1)
      .build();
    new Runner(opt).run();
  }
}