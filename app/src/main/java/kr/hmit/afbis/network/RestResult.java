package kr.hmit.afbis.network;

import kr.hmit.afbis.model.response.BaseModel;

public class RestResult {
    public final BaseModel mData;

    public RestResult(BaseModel baseModel) {
        mData = baseModel;
    }

    public static RestResult from(BaseModel baseModel) {
        return new RestResult(baseModel);
    }
}
