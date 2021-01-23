cd app\src\main\java\com\amazaar\Protos
echo [COMPILEING..........]
protoc addressPb.proto --java_out=..\..\..\
protoc contactDetailPb.proto --java_out=..\..\..\
protoc customerPb.proto --java_out=..\..\..\
protoc deliveryManPb.proto --java_out=..\..\..\
protoc entityPb.proto --java_out=..\..\..\
protoc genderPb.proto --java_out=..\..\..\
protoc imagePb.proto --java_out=..\..\..\
protoc itemPb.proto --java_out=..\..\..\
protoc loginPb.proto --java_out=..\..\..\
protoc namePb.proto --java_out=..\..\..\
protoc requestPb.proto --java_out=..\..\..\
protoc summaryPb.proto --java_out=..\..\..\
protoc timePb.proto --java_out=..\..\..\
protoc registrationPb.proto --java_out=..\..\..\
protoc pushNotificationPb.proto --java_out=..\..\..\
protoc cartPb.proto --java_out=..\..\..\
protoc buyPb.proto --java_out=..\..\..\
protoc paymentPb.proto --java_out=..\..\..\
protoc orderedListPb.proto --java_out=..\..\..\
protoc confirmOrderDeliveryPb.proto --java_out=..\..\..\

cd ..
cd ..
cd ..
cd ..
cd ..
cd ..
cd ..

