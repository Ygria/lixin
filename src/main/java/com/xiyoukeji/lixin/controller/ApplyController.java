package com.xiyoukeji.lixin.controller;

import com.xiyoukeji.lixin.annotation.Access;
import com.xiyoukeji.lixin.dto.ApplyDTO;
import com.xiyoukeji.lixin.service.ApplyService;
import com.xiyoukeji.lixin.type.AdminRoleEnum;
import com.xiyoukeji.lixin.type.ApplyStatus;
import com.xiyoukeji.lixin.util.MapTool;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by ygria on 2018/2/5.
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Resource
    ApplyService applyService;

    @RequestMapping("/save")
    public Map saveApply(@RequestBody @Valid ApplyDTO applyDTO){

        return applyService.saveApply(applyDTO);

    }

    @Access(value = AdminRoleEnum.NORMAL)
    @RequestMapping("/list")
    public Map listApplies(Integer pageNum, Integer rowNum, ApplyStatus status, String search_key){
        return applyService.listApply(pageNum, rowNum, status,search_key);
    }

    @Access(value = AdminRoleEnum.NORMAL)
    @RequestMapping("/set_apply_status")
    public Map setApplyStatus(Long id,ApplyStatus status){

        applyService.setApplyStatus(id, status);
        return MapTool.Mapok();
    }

}
