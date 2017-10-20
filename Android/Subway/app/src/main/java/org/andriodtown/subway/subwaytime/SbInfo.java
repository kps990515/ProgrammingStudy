package org.andriodtown.subway.subwaytime;

/**
 * Created by user on 2017-10-20.
 */

public class SbInfo {
    private ErrorMessage errorMessage;

    private RealtimeArrivalList[] realtimeArrivalList;

    public ErrorMessage getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (ErrorMessage errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public RealtimeArrivalList[] getRealtimeArrivalList ()
    {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList (RealtimeArrivalList[] realtimeArrivalList)
    {
        this.realtimeArrivalList = realtimeArrivalList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [errorMessage = "+errorMessage+", realtimeArrivalList = "+realtimeArrivalList+"]";
    }
}
