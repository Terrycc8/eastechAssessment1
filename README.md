# eastechAssessment1

Prerequisites:

Make sure you have an AWS account set up.
Step 1: Set up an S3 Bucket for Hosting

Go to the AWS Management Console and navigate to the S3 service.

Click on "Create bucket" and provide a unique name for your bucket (e.g., "my-react-frontend").

Choose the region where you want to host your bucket.

Leave all other settings as default and click "Create bucket".

Once the bucket is created, select it from the list and go to the "Properties" tab.

Click on "Static website hosting" and select the option "Use this bucket to host a website".

Enter "index.html" as the Index document and optionally specify an error document.

Note down the Endpoint URL mentioned under the "Static website hosting" section.

Step 2: Build and Deploy the React Frontend

In your React project, open the terminal and run the following command to create a production build:

Copy
npm run build

```

After the build process completes, a build folder will be generated in your project directory.

Go back to the AWS S3 Management Console and select your bucket.

Click on the "Upload" button, and in the file selection dialog, navigate to your project's build folder.

Select all the files and folders in the build folder and click "Upload".

Once the files are uploaded, make sure the permissions are set to allow public read access to your objects. Select all the files, click on "Actions", and choose "Make public".

Test the deployment by accessing the Endpoint URL noted down earlier. You should see your React frontend application being served from the S3 bucket.

Step 3: Set up Route 53

Go to the AWS Management Console and navigate to the Route 53 service.

Click on "Hosted zones" and then "Create hosted zone".

Enter your domain name (e.g., "example.com") and click "Create".

AWS will provide you with a set of nameservers. Take note of these nameservers.

In your domain registrar's settings, update the nameservers to the ones provided by Route 53. This will delegate DNS resolution to Route 53.

Step 4: Set up CloudFront

Go to the AWS Management Console and navigate to the CloudFront service.

Click on "Create Distribution".

Select "Web" as the delivery method.

In the "Origin Settings" section, select your S3 bucket as the Origin Domain Name.

Configure other settings as needed, such as cache behaviors, SSL certificate, and default root object.

Review the configuration and click "Create Distribution".

Once the distribution is created (it may take some time to deploy), note down the CloudFront domain name provided in the distribution details.

Step 5: Update DNS Settings

Go back to the Route 53 console and select your hosted zone.

Click on "Create Record Set".

Enter the desired subdomain or leave it blank for the root domain.

Set the record type to "A" and in the "Value/Route traffic to" section, select "Alias to CloudFront distribution".

Choose your CloudFront distribution from the dropdown.

Set a TTL value (e.g., 300 seconds).

Click "Create" to save the record.

Step 6: Test the Deployment

Wait for DNS propagation to complete (which may take some time).

Access your domain in a web browser. The request will be routed through Route 53 to CloudFront, which will serve your React frontend application from the S3 bucket.

Confirm that your React frontend application is accessible via the domain, and CloudFront is correctly serving the content.
```
