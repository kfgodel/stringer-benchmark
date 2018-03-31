package ar.com.kfgodel.stringer;

/**
 * This class represents a simple object with primitive properties
 * Date: 17/03/18 - 22:16
 */
public class ExamplePojo {

  private Long id;
  public static final String id_FIELD = "id";

  private String name;
  public static final String name_FIELD = "name";

  private String telephone;
  public static final String telephone_FIELD = "telephone";

  private int age;
  public static final String age_FIELD = "age";

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public static ExamplePojo createDefault() {
    ExamplePojo pojo = new ExamplePojo();
    pojo.age = 24;
    pojo.id = 1L;
    pojo.name = "Pepe";
    pojo.telephone = "4544-464";
    return pojo;
  }

}
