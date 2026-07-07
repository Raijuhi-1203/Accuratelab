package digitalbull.app.accuratelab.Services;

public class NetParam
{
    private String key;
    private String value;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public NetParam(String _key,String _value)
    {
      this.key=_key;this.value=_value;
    }
}
