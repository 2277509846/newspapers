package com.example.newspapers.service.impl;

import com.example.newspapers.dao.AddressDao;
import com.example.newspapers.dao.NewspaperDao;
import com.example.newspapers.dao.SubmitDao;
import com.example.newspapers.entity.Submit;
import com.example.newspapers.service.SubmitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SubmitServiceImpl implements SubmitService {
    @Resource
    SubmitDao submitDao;
    @Resource
    NewspaperDao newspaperDao;
    @Resource
    AddressDao addressDao;
    @Override
    public List<Submit> submitList(String username) {
        List<Submit> submits = submitDao.submitList(username);
        fix(submits);
        return submits;
    }

    private void fix(List<Submit> submits) {
        for (Submit submit : submits) {
            String newspaperName = newspaperDao.findNewspaperById(submit.getNewspaperId()).getName();
            String addressName = addressDao.findAddressNameById(submit.getAddressId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDateStr = simpleDateFormat.format(submit.getStartDate());
            String endDateStr = simpleDateFormat.format(submit.getEndDate());
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String submitTimeStr = simpleDateFormat.format(submit.getSubmitTime());
            submit.setNewspaperName(newspaperName);
            submit.setAddressName(addressName);
            submit.setStartDateStr(startDateStr);
            submit.setEndDateStr(endDateStr);
            submit.setSubmitTimeStr(submitTimeStr);
            if (submit.getStatus() == 1) submit.setStatusStr("已办理");
            else submit.setStatusStr("未办理");
            submit.setFeeStr("￥" + submit.getFee());
        }
    }

    @Override
    public List<Submit> submitListByAdminUsername(String adminUsername) {
        List<Submit> submits = submitDao.submitListByAdminUsername(adminUsername);
        fix(submits);
        return submits;
    }

    @Override
    public void manage(Integer newspaperId, String username, String submitTimeStr) {
        submitDao.manage(newspaperId, username, submitTimeStr);
    }

    @Override
    public void submit(Submit submit) {
        submit.setSubmitTime(new Date());
        submitDao.submit(submit);
    }


}
