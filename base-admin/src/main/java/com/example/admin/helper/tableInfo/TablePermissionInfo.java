package com.example.admin.helper.tableInfo;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class TablePermissionInfo {
    private boolean hasCreator;
    private boolean hasOrganization;
}
