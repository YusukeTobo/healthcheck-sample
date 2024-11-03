# healthcheck-sample
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
