package com.clsaa.dop.server.code.service;

import com.clsaa.dop.server.code.model.bo.commit.CommitBo;
import com.clsaa.dop.server.code.util.RequestUtil;
import com.clsaa.dop.server.code.util.TimeUtil;
import com.clsaa.dop.server.code.util.URLUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 提交服务类
 *
 * @author wsy
 */
@Service
public class CommitService {

    /**
     * 查看项目所有提交的列表
     *
     * @param id       项目id
     * @param userId   用户id
     * @param ref_name tag名或分支名
     * @param path     路径(需要urlencode)
     * @return 提交列表
     */
    public List<CommitBo> findCommitList(String id, String path, String ref_name, Long userId) throws UnsupportedEncodingException {

        id = URLUtil.encodeURIComponent(id);

        String url = "/projects/" + id + "/repository/commits?ref_name=" + ref_name;
        //若不为根目录
        if (!path.equals("/")) {
            url += "&path=" + URLUtil.encodeURIComponent(path);
        }

        List<CommitBo> commitBos = RequestUtil.getList(url, userId, CommitBo.class);


        for (CommitBo commitBo : commitBos) {
            List<String> strs = TimeUtil.natureTime(commitBo.getAuthored_date());
            commitBo.setAuthored_date(strs.get(0));
            commitBo.setAuthored_time(strs.get(1));
        }

        return commitBos;

    }

}
