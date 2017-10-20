package org.andriodtown.subway.model.Station.station;

/**
 * Created by user on 2017-10-19.
 */

public class StInfo
{
    private org.andriodtown.subway.model.Station.station.SearchSTNBySubwayLineService SearchSTNBySubwayLineService;

    public SearchSTNBySubwayLineService getSearchSTNBySubwayLineService ()
    {
        return SearchSTNBySubwayLineService;
    }

    public void setSearchSTNBySubwayLineService (SearchSTNBySubwayLineService SearchSTNBySubwayLineService)
    {
        this.SearchSTNBySubwayLineService = SearchSTNBySubwayLineService;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [SearchSTNBySubwayLineService = "+SearchSTNBySubwayLineService+"]";
    }
}
