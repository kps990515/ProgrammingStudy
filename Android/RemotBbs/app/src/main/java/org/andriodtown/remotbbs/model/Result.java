package org.andriodtown.remotbbs.model;

/**
 * Created by user on 2017-10-26.
 */

public class Result
{
    private Data[] data;

    private String code;

    private String msg;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    public boolean getSucess(){
        return "200".equals(code);
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", code = "+code+", msg = "+msg+"]";
    }
}
