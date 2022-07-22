/*-
 * #%L
 * Magpie API
 * %%
 * Copyright (C) 2021 Open Raven Inc
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
/*
 * Copyright 2021 Open Raven Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openraven.magpie.data.aws.transitgateway;

import io.openraven.magpie.data.aws.AWSResource;
import io.openraven.magpie.data.aws.shared.PayloadUtils;
import software.amazon.awssdk.services.ec2.model.TransitGateway;

import static java.lang.String.format;

@javax.persistence.Entity
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.TABLE_PER_CLASS)
@javax.persistence.Table(name = TransitGatewayGateway.TABLE_NAME)
public class TransitGatewayGateway extends AWSResource {

  protected static final String TABLE_NAME = "awsec2transitgateway";
  public static final String RESOURCE_TYPE = "AWS::EC2::TransitGateway";

  public TransitGatewayGateway() {
    this.resourceType = RESOURCE_TYPE;
  }

  public TransitGatewayGateway(String account, String region, TransitGateway transit) {
    this.awsRegion = region;
    this.awsAccountId = account;
    this.arn = format("arn:aws:ec2:%s:%s:transit-gateway/%s", region, account,
      transit.transitGatewayId());
    this.resourceName = transit.transitGatewayId();
    this.resourceId = transit.transitGatewayId();
    this.configuration = PayloadUtils.update(transit);
    this.resourceType = TransitGatewayGateway.RESOURCE_TYPE;
  }
}
