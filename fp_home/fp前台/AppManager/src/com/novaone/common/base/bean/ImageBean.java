package com.novaone.common.base.bean;

import javax.activation.DataHandler;
/**
 * 图片模型
 * @author chengql
 * @date 2013-11-13
 */
public class ImageBean {
	private String candidateName;   
    private String resumeFileType;   
    private DataHandler resume;  
    public String getCandidateName() {  
        return candidateName;  
    }  
    public void setCandidateName(String candidateName) {  
        this.candidateName = candidateName;  
    }  
    public String getResumeFileType() {  
        return resumeFileType;  
    }  
    public void setResumeFileType(String resumeFileType) {  
        this.resumeFileType = resumeFileType;  
    }  
    public DataHandler getResume() {  
        return resume;  
    }  
    public void setResume(DataHandler resume) {  
        this.resume = resume;  
    }  
}
