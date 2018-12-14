PIPELINE_SOURCE=$1
PROJECT_NAME=$2
PIPELINE_URL=$3
JOB_NAME=$4
TEMPLATE=$5
ACTION_TYPE=$6
RELEASE_VERSION=$7


curl -X POST -H "Content-type: application/json" \
	--data '{"ciPipeLineSource":"'"${PIPELINE_SOURCE}"'","ciProjectName":"'"${PROJECT_NAME}"'","ciPipelineUrl":"'"${PIPELINE_URL}"'","ciJobName":"'"${JOB_NAME}"'","template":"'"${TEMPLATE}"'","actionType":"'"${ACTION_TYPE}"'","ciReleaseVersion":"'"${RELEASE_VERSION}"'"}' \
	http://192.168.130.120:8080/api/send/slack
	
#template
#./slackNotification.sh $CI_PIPELINE_SOURCE $CI_PROJECT_NAME $CI_PIPELINE_URL $CI_PROJECT_NAME %template id% %actionType id% %release version%

#Build up notification
#./slackNotification.sh push contact-back-end whwieufhw release_build 3 1 1.3
#Job end notification
#./slackNotification.sh push contact-back-end whwieufhw release_build 2 2