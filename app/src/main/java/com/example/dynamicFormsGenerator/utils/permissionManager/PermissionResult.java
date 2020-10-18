package com.example.dynamicFormsGenerator.utils.permissionManager;


public interface PermissionResult {

    void permissionGranted();

    void permissionDenied();

    void permissionForeverDenied();

}
