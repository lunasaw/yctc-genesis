package edu.yctc.face;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import edu.yctc.common.constant.BaiduApiContent;
import edu.yctc.common.utils.file.Base64Utils;
import edu.yctc.common.utils.file.FileUtils;
import edu.yctc.common.utils.file.FileUtilsAlter;
import edu.yctc.common.utils.http.HttpUtilsLuna;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/4/20 11:46
 */
public class OcrBaiduApi {

    public static List<String> baiDuOcr(String img) throws IOException {
        String path = ResourceUtils.getURL("classpath:static/").getPath();

        File file = new File(path + "tmp.jpg");
        if (file.exists()) {
            FileUtils.deleteFile(path + "tmp.jpg");
        }

        String s = null;
        if (HttpUtilsLuna.isNetUrl(img)) {
	        try {
		        FileUtilsAlter.downloadHttpUrl(img, path, "tmp.jpg");
                s = Base64Utils.GetImageStr(path + "tmp.jpg");
	        } catch (IOException e) {
	        	return new ArrayList<>();
	        }
        } else {
            s = Base64Utils.GetImageStr(img);
        }

        HttpResponse httpResponse = HttpUtilsLuna.doPost(BaiduApiContent.HOST, BaiduApiContent.OCR,
            ImmutableMap.of("Content-Type", "application/x-www-form-urlencoded"), null,
            ImmutableMap.of("access_token", BaiduApiContent.KEY, "image", s));
        JSONObject response = HttpUtilsLuna.getResponse(httpResponse);
        List<String> words = new ArrayList<>();
        int i = Integer.parseInt(response.get("words_result_num").toString());
        JSONArray array = new JSONArray(response.get("words_result").toString());
        for (int i1 = 0; i1 < i; i1++) {
            String word = array.getJSONObject(i1).getString("words");
            words.add(word);
        }
        return words;
    }

}
