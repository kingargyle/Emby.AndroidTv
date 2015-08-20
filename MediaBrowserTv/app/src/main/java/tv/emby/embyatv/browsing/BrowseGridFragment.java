package tv.emby.embyatv.browsing;

import android.os.Bundle;

import mediabrowser.apiinteraction.EmptyResponse;
import mediabrowser.model.querying.ItemFields;
import tv.emby.embyatv.TvApp;
import tv.emby.embyatv.querying.StdItemQuery;

/**
 * Created by Eric on 8/16/2015.
 */
public class BrowseGridFragment extends StdGridFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected void setupQueries(IGridLoader gridLoader) {
        StdItemQuery query = new StdItemQuery(new ItemFields[] {ItemFields.PrimaryImageAspectRatio});
        query.setParentId(mParentId);
        if (mFolder.getType().equals("UserView")) {
            String type = mFolder.getCollectionType() != null ? mFolder.getCollectionType().toLowerCase() : "";
            query.setRecursive(true);
            switch (type) {
                case "movies":
                    query.setIncludeItemTypes(new String[]{"Movie"});
                    break;
                case "tvshows":
                    query.setIncludeItemTypes(new String[]{"Series"});
                    break;
            }
        }

        mRowDef = new BrowseRowDef("", query, 150, false, true);

        gridLoader.loadGrid(mRowDef);
    }

}