Instruction:
1. Install grafana - change defualt port
2. Install simple json data source
3. Start grafana client
4. Main excpects path of properties file, and table definition file
5. In properties file fill in host and port properties of the service
6. In properties file fill in remote space properties, see grafana-insightedge.properties file.
7. In tables file each line contain display name of table in grafana and fields details, see tablesData.txt file
8. Currently no aggregation in space are done, Reading all data in range at once.
9. Excpecting date fields as long.
10. Configur grafana simple json data source with url of this service.e.g:
http://yourhost:yourport/insightedge/metrics
