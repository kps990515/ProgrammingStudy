package org.andriodtown.transferstation.model;

/**
 * Created by user on 2017-10-17.
 */

public class SubTran
{
    private StationDayTrnsitNmpr StationDayTrnsitNmpr;

    public StationDayTrnsitNmpr getStationDayTrnsitNmpr ()
    {
        return StationDayTrnsitNmpr;
    }

    public void setStationDayTrnsitNmpr (StationDayTrnsitNmpr StationDayTrnsitNmpr)
    {
        this.StationDayTrnsitNmpr = StationDayTrnsitNmpr;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [StationDayTrnsitNmpr = "+StationDayTrnsitNmpr+"]";
    }
}
