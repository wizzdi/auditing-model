package com.flexicore.audit.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flexicore.interfaces.dynamic.FieldInfo;
import com.flexicore.interfaces.dynamic.IdRefFieldInfo;
import com.flexicore.model.FilteringInformationHolder;
import com.flexicore.model.Operation;
import com.flexicore.model.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AuditingFilter extends FilteringInformationHolder {

    @FieldInfo
    private String auditTypeLike;
    @FieldInfo
    private String userNameLike;
    @FieldInfo
    private String operationNameLike;
    @OneToMany(targetEntity = OperatingIdRef.class,mappedBy = "filteringInformationHolder",fetch = FetchType.EAGER)
    @IdRefFieldInfo(refType = Operation.class)
    private Set<OperatingIdRef> operationIds=new HashSet<>();
    @JsonIgnore
    @Transient
    private List<Operation> operations;
    @OneToMany(targetEntity = UserIdRef.class,mappedBy = "filteringInformationHolder",fetch = FetchType.EAGER)
    @IdRefFieldInfo(refType = User.class)
    private Set<UserIdRef> userIds=new HashSet<>();
    @JsonIgnore
    @Transient
    private List<User> users;

    public String getAuditTypeLike() {
        return auditTypeLike;
    }

    public <T extends AuditingFilter> T setAuditTypeLike(String typeLike) {
        this.auditTypeLike = typeLike;
        return (T) this;
    }

    @OneToMany(targetEntity = OperatingIdRef.class,mappedBy = "filteringInformationHolder",fetch = FetchType.EAGER)

    public Set<OperatingIdRef> getOperationIds() {
        return operationIds;
    }

    public <T extends AuditingFilter> T setOperationIds(Set<OperatingIdRef> operationIds) {
        this.operationIds = operationIds;
        return (T) this;
    }

    @JsonIgnore
    @Transient
    public List<Operation> getOperations() {
        return operations;
    }

    public <T extends AuditingFilter> T setOperations(List<Operation> operations) {
        this.operations = operations;
        return (T) this;
    }

    @OneToMany(targetEntity = UserIdRef.class,mappedBy = "filteringInformationHolder",fetch = FetchType.EAGER)
    public Set<UserIdRef> getUserIds() {
        return userIds;
    }

    public <T extends AuditingFilter> T setUserIds(Set<UserIdRef> userIds) {
        this.userIds = userIds;
        return (T) this;
    }

    @JsonIgnore
    @Transient
    public List<User> getUsers() {
        return users;
    }

    public <T extends AuditingFilter> T setUsers(List<User> users) {
        this.users = users;
        return (T) this;
    }

    public String getUserNameLike() {
        return userNameLike;
    }

    public <T extends AuditingFilter> T setUserNameLike(String userNameLike) {
        this.userNameLike = userNameLike;
        return (T) this;
    }

    public String getOperationNameLike() {
        return operationNameLike;
    }

    public <T extends AuditingFilter> T setOperationNameLike(String operationNameLike) {
        this.operationNameLike = operationNameLike;
        return (T) this;
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        for (OperatingIdRef operationId : operationIds) {
            operationId.prepareForSave(this);
        }
        for (UserIdRef userId : userIds) {
            userId.prepareForSave(this);
        }
    }
}
