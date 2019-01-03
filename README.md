To use Grafana for data visualization with InsightEdge, you will have to do the following:

1. Download and configure the InsightEdge-Grafana connector (customize the *grafana-isnightedge.properties* and *tablesData.txt* files).
2. Download and install the Grafana client from the Grafana website (http://grafana.org/download/).
3. Download and install the Grafana SimpleJson data source plugin, available from the Grafana website (https://grafana.com/plugins/grafana-simple-json-datasource/installation).
4. Change the default port of the Grafana client (the default port is usually blocked).
5. In the Grafana client Settings tab, configure the data source and apply the URL for the InsightEdge-Grafana connector.


For detailed instructions on how to integrate InsightEdge and Grafana, see the Solutions and Best Practices section (https://docs.gigaspaces.com/sbp/) of the GigaSpaces documentation website.
