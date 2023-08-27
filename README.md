## Detailed instructions for frontend deployment

### Step 1: Set up an S3 Bucket for Hosting

1. Go to the AWS Management Console and navigate to the S3 service.
2. Click on "Create bucket" and provide a unique name for your bucket (e.g., "my-react-frontend").
3. Choose the region where you want to host your bucket.
4. Leave all other settings as default and click "Create bucket".
5. Once the bucket is created, select it from the list and go to the "Properties" tab.
6. Click on "Static website hosting" and select the option "Use this bucket to host a website".
7. Enter "index.html" as the Index document and optionally specify an error document.
8. Note down the Endpoint URL mentioned under the "Static website hosting" section.

### Step 2: Build and Deploy the React Frontend

1. In your React project, open the terminal and run 'npm run build' to create a production build:
2. After the build process completes, a build folder will be generated in your project directory.
3. Go back to the AWS S3 Management Console and select your bucket.
4. Click on the "Upload" button, and in the file selection dialog, navigate to your project's build folder.
5. Select all the files and folders in the build folder and click "Upload".
6. Once the files are uploaded, make sure the permissions are set to allow public read access to your objects. Select all the files, click on "Actions", and choose "Make public".
7. Test the deployment by accessing the Endpoint URL noted down earlier. You should see your React frontend application being served from the S3 bucket.

### Step 3: Set up Route 53

1. Go to the AWS Management Console and navigate to the Route 53 service.
2. Click on "Hosted zones" and then "Create hosted zone".
3. Enter your domain name (e.g., "example.com") and click "Create".
4. AWS will provide you with a set of nameservers. Take note of these nameservers.
5. In your domain registrar's settings, update the nameservers to the ones provided by Route 53. This will delegate DNS resolution to Route 53.

### Step 4: Set up CloudFront

1. Go to the AWS Management Console and navigate to the CloudFront service.
2. Click on "Create Distribution".
3. Select "Web" as the delivery method.
4. In the "Origin Settings" section, select your S3 bucket as the Origin Domain Name.
5. Configure other settings as needed, such as cache behaviors, SSL certificate, and default root object.
6. Review the configuration and click "Create Distribution".
7. Once the distribution is created (it may take some time to deploy), note down the CloudFront domain name provided in the distribution details.

## Step 5: Update DNS Settings

1. Go back to the Route 53 console and select your hosted zone.
2. Click on "Create Record Set".
3. Enter the desired subdomain or leave it blank for the root domain.
4. Set the record type to "A" and in the "Value/Route traffic to" section, select "Alias to CloudFront distribution".
5. Choose your CloudFront distribution from the dropdown.
6. Set a TTL value (e.g., 300 seconds).
7. Click "Create" to save the record.

### Step 6: Test the Deployment

1. Wait for DNS propagation to complete (which may take some time).
2. Access your domain in a web browser. The request will be routed through Route 53 to CloudFront, which will serve your React frontend application from the S3 bucket.
3. Confirm that your React frontend application is accessible via the domain, and CloudFront is correctly serving the content.

## Detailed instructions for backend deployment

### Step 1: Set up an EC2 Instance

1. Go to the AWS Management Console and navigate to the EC2 service.
2. Click on "Launch Instance" to create a new EC2 instance.
3. Choose an Amazon Machine Image (AMI) that supports Java 17 and meets your requirements.
4. Select the desired instance type, configure instance details, and set up storage as needed.
5. Configure security groups to allow inbound connections to the necessary ports (e.g., HTTP/HTTPS for NGINX and your backend application).
6. Review the configuration and click "Launch".
7. Select an existing key pair or create a new one to connect to your EC2 instance securely.
8. Launch the instance.

### Step 2: Connect to the EC2 Instance

1. Once the EC2 instance is running, select it from the list in the EC2 Management Console.
2. Note the public IP or public DNS of the instance.
3. Open a terminal or SSH client and connect to the EC2 instance using the following command: ssh -i <path-to-your-key-pair> ec2-user@<public-ip-or-public-dns>

### Step 3: Install Docker and NGINX on the EC2 Instance

1. Update the package manager on the EC2 instance.
2. Add the current user to the docker group to run Docker commands without sudo.

### Step 4: Build and Run the Java Spring Boot Backend Application

1. Transfer your Java Spring Boot backend project to the EC2 instance using a secure method like SCP or SFTP.
2. In the terminal connected to the EC2 instance, navigate to your project directory.
3. Build your Java Spring Boot application using a build tool like Gradle.
4. Build a Docker image for your backend application.
5. Run the Docker container with your backend application.

### Step 5: Configure NGINX as a Reverse Proxy

1. Open the NGINX configuration file using a text editor.
2. Modify the server block to configure NGINX as a reverse proxy to your backend application.

```
server {
    listen       80;
    server_name  example.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

3. Replace example.com with your domain name or public IP.
4. Save the changes and exit the text editor.
5. Restart NGINX to apply the configuration changes.

### Step 6: Test the Deployment

1. Open a web browser and visit your EC2 instance's public IP or domain.
2. You should see your Java Spring Boot backend application being served through NGINX.
3. Test the functionality of your backend application to ensure it is working as expected.
