package com.xiyoukeji.lixin.service;

import com.xiyoukeji.lixin.domain.Apply;
import com.xiyoukeji.lixin.dto.ApplyDTO;
import com.xiyoukeji.lixin.repository.BaseDao;
import com.xiyoukeji.lixin.type.ApplyStatus;
import com.xiyoukeji.lixin.util.MapTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ygria on 2018/2/5.
 */
@Service
public class ApplyService {
    @Resource
    BaseDao baseDao;


    @Transactional
    public Map saveApply(ApplyDTO applyDTO){
        Apply apply = new Apply();
        apply.setMoney(applyDTO.getMoney());
        apply.setCompany_name(applyDTO.getCompany_name());
        apply.setUsage_of_loan(applyDTO.getUsage_of_loan());
        apply.setStart_time(applyDTO.getStart_time());
        apply.setEnd_time(applyDTO.getEnd_time());
        apply.setApply_time(applyDTO.getApply_time());
        apply.setWechat(applyDTO.getWechat());
        //用户提出申请时，默认申请状态为待处理（TODO）
        apply.setStatus(ApplyStatus.TODO);
        baseDao.save(apply);
        Map map = new HashMap();
        map.put("state",0);
        return map;

    }

    @Transactional
    public Map listApply(Integer pageNum, Integer rowNum, ApplyStatus status,String search_key){
        pageNum = pageNum==null?1:pageNum;
        rowNum = rowNum==null?10:rowNum;
        String hql = "from Apply apply where 1=1";
        Map param = new HashMap();
        if(status!=null){
            hql +=" and apply.status =:status";
            param.put("status",status);
        }
        if(search_key!=null){
            hql+=" and (apply.company_name like :search_key or apply.wechat like :search_key)";
            param.put("search_key","%"+search_key+"%");
        }
        List<Apply> applies =  baseDao.find(hql,(pageNum-1)*rowNum,rowNum,param);
        MapTool data = MapTool.Map().put("data", applies).put("pageNum", pageNum).put("rowNum", rowNum).put("totalPageNum", ((Double) Math.ceil((double) baseDao.count(hql, param) / rowNum)).intValue());
        return MapTool.Mapok().put("data",data);

    }

    @Transactional
    public void setApplyStatus(Long id,ApplyStatus status){
        Apply apply = (Apply) baseDao.get(Apply.class,id);
        apply.setStatus(status);
        baseDao.update(apply);

    }


}
