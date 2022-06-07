# station map deatils Service Rest Full API Service

This service will provide near by station details to users based ong their preferred criteria

Contains reference implementation of few operations kinda
* db
* conversion
* error handling
* logging


Used below tech stack for developement.
* spring boot
* h2 databse
* java 11
* jpa

Authenticated Users:

* user/user - USER
* admin/admin - ADMIN

Endpoint Details:

1./findStationMap  - only USER&ADMIN have access for this endpoint.
This will filter data based on user preferred distance & criteria 
