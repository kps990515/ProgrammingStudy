package org.andriodtown.subway.model.Station;

/**
 * Created by user on 2017-10-19.
 */

public class StInfo {
    private SearchSTNBySubwayLineService SearchSTNBySubwayLineService;

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
