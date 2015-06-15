/**
 * Copyright (c) 2010-2015 meituan.com
 * All rights reserved.
 */
package com.yueqiu.loader;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.yueqiu.model.Game;
import com.yueqiu.model.Representation;
import com.yueqiu.utils.Constants;
import com.yueqiu.utils.HttpUtils;
import com.yueqiu.utils.JsonUtils;
import com.yueqiu.utils.Logger;
import com.yueqiu.widget.BaseAsyncTaskLoader;

import java.util.List;
import java.util.Map;

/**
 * TODO 在这里编写类的功能描述
 *
 * @author lidehua
 * @version 1.0
 * @since 4.1
 */
public class GameListLoader extends BaseAsyncTaskLoader<List<Game>> {

    public GameListLoader(Context context) {
        super(context);
    }

    @Override
    public List<Game> loadInBackground() {
        Map<String, Object> params = getParams(false);
        params.put("dateType", "0");
        String ret = HttpUtils.get(Constants.API_HOST + "/1/activities", params);
        Logger.debug("Loader", ret);
        Representation<List<Game>> rep = JsonUtils.fromJson(ret, new TypeToken<Representation<List<Game>>>() {
        }.getType());
        return getData(rep);
    }
}
