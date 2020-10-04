# EasyPermission-Android

Android library for request permissions &amp;&amp; check permission.

### Usage - Driect ( request permissions )

List<String> permissions = new ArrayList<>();
 
permissions.add(Manifest.permission.READ_PHONE_STATE); 
permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

EasyPermission.requestPermissions(this,permissions,100);

### Usage - Void ( request permissions )

List<String> permissions = new ArrayList<>();
 
permissions.add(Manifest.permission.READ_PHONE_STATE); 
permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

EasyPermission permission = new EasyPermission(this);
permission.requestPermissions(permissions,100);

### Usage - Driect ( check permission )

if(EasyPermission.checkPermission(this,Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED){ }
if(EasyPermission.checkPermission(this,Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){ }

### Usage - Void ( check permission )

EasyPermission permission = new EasyPermission(this);

if(permission.checkPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED){ }
if(permission.checkPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){ }
