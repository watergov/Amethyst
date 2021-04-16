package me.lucyy.watercore.api.data;

import java.io.Serializable;
import java.util.Objects;
import me.lucyy.watercore.api.data.DataKey;
import me.lucyy.watercore.api.module.WaterModule;

public class MySQLKey<T extends Serializable> extends DataKey  {

  private final String key;

  public MySQLKey(WaterModule parent, String child, String key, Class<T> clazz) {
    super(parent, child, clazz);
    this.key = key;
  }


  @Override
  public String toString(){
    return String.format("Table: %s where %s = %s", getParent(), getChild(), key);
  }

  public String getKey(){return key;}

  @Override
  public int hashCode(){return Objects.hash(getParent(), getChild(), key);}
}
