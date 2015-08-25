package com.caihua.camera;

import java.io.Serializable;
import java.util.List;

public class IdCardInfo implements Serializable
{
  private static final long serialVersionUID = 409085379395233356L;
  private String address;
  private String authority;
  private String birthday;
  private String folk;
  private String head;
  private String img;
  private String memo;
  private String name;
  private String num;
  private int resultType;
  private String sex;
  private List<String> testRtime;
  private String valid;

  public static long getSerialversionuid()
  {
    return 409085379395233356L;
  }

  public String getAddress()
  {
    return this.address;
  }

  public String getAuthority()
  {
    return this.authority;
  }

  public String getBirthday()
  {
    return this.birthday;
  }

  public String getFolk()
  {
    return this.folk;
  }

  public String getHead()
  {
    return this.head;
  }

  public String getImg()
  {
    return this.img;
  }

  public String getMemo()
  {
    return this.memo;
  }

  public String getName()
  {
    return this.name;
  }

  public String getNum()
  {
    return this.num;
  }

  public int getResultType()
  {
    return this.resultType;
  }

  public String getSex()
  {
    return this.sex;
  }

  public List<String> getTestRtime()
  {
    return this.testRtime;
  }

  public String getValid()
  {
    return this.valid;
  }

  public void setAddress(String paramString)
  {
    this.address = paramString;
  }

  public void setAuthority(String paramString)
  {
    this.authority = paramString;
  }

  public void setBirthday(String paramString)
  {
    this.birthday = paramString;
  }

  public void setFolk(String paramString)
  {
    this.folk = paramString;
  }

  public void setHead(String paramString)
  {
    this.head = paramString;
  }

  public void setImg(String paramString)
  {
    this.img = paramString;
  }

  public void setMemo(String paramString)
  {
    this.memo = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setNum(String paramString)
  {
    this.num = paramString;
  }

  public void setResultType(int paramInt)
  {
    this.resultType = paramInt;
  }

  public void setSex(String paramString)
  {
    this.sex = paramString;
  }

  public void setTestRtime(List<String> paramList)
  {
    this.testRtime = paramList;
  }

  public void setValid(String paramString)
  {
    this.valid = paramString;
  }
}
