package com.bakigoal.mongodb.gson.model;

public class CustomerInfo {
  private String name;
  private Info info;

  public CustomerInfo(String name, int age, String email, String phone) {
    this.name = name;
    this.info = new Info(age, email, phone);
  }

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "CustomerInfo [name=" + name + ", info=" + this.info + "] ";
  }

  class Info {
    private String email;
    private String phone;
    private int age;

    Info(int age, String email, String phone) {
      this.email = email;
      this.phone = phone;
      this.age = age;
    }

    public Info() {
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPhone() {
      return phone;
    }

    public void setPhone(String phone) {
      this.phone = phone;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "Info [email=" + email + ", phone=" + phone + ", age = " + age + "]";
    }
  }
}
