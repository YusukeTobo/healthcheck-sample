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
| STARTUP_DELAY_MS |120000 | How long this app takes to open its HTTP port. |
| ALLOWLIST_FILE | C:\\\\home\\\\allowlist.txt | Specify instances that skip startup delay. |

## allowlist.txt
The allowlist must be line separated value.
```
InstanceA
InstanceB
InstanceC
```
