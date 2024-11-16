# healthcheck-sample
Test application for Health Check feature in App Service (Java21+).
## Quick start
### Build
```shell
./mvnw package
```
### Run
```
java -jar ./target/healthcheck-0.0.1-SNAPSHOT.jar
```
## Environment Variables

| Name | Default | Explain |
----| ---- | ----
| STARTUP_DELAY_MS |120000 | Specifies how long (in milliseconds) the app takes to open its HTTP port during startup. |
| BYPASSLIST_FILE | C:\\\\home\\\\bypasslist.txt | Specifies instances that bypass startup delay configured in STARTUP_DELAY_MS. |
| UNHEALTHYLIST_FILE | C:\\\\home\\\\unhealthylist.txt | Specifies instances that returns HTTP 503. |

## bypasslist.txt / unhealthylist.txt
The list must be line-separated values.
```
InstanceA
InstanceB
InstanceC
```
