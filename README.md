# LiDAR
## Client Description
Lloyd’s Register are one of the world’s leading providers of professional services for engineering and technology with a focus on improving safety and increasing the performance of critical infrastructures worldwide. The profits that the company makes fund the Lloyd’s Register Foundation, a charity which supports engineering-related research, education and public engagement around everything we do.The team you’ll be working with are the Energy Resource Services team within Renewables (David, Tim, Matt,Nathan and Andoni). We provide consultancy services to the renewable energy industry, primarily focusing on calculating and optimising how much energy can be produced by your wind/solar farm whether it is in the feasibility, design or operational phase of its life cycle.

## Motivation
When assessing the feasibility of an offshore wind farm it is necessary to measure the wind conditions (speed, direction etc.)  to know firstly whether it is windy enough to justify the cost of building i.e. whether it is profitable. Secondly the wind conditions that are measured are then used for detailed design,ensuring that the site layout, turbine selected are optimal for the site. In the last few years a new technology has been taken over the market: floating LiDAR. This technology uses lasers mounted on a floating buoy to measure the wind speed at heights up to 200m above the sea surface.As this is a new technology each device requires a validation before it is put out on-site.  This involves putting the floating LiDAR near to an existing trusted fixed measurement device and checking that certain key performance indicators (KPIs) are met. Currently this process is undertaken manually which is fairly inefficient,involving engineers analysing the data on a daily or weekly basis until the KPIs are met. I’m confident that this process could be automated to provide everyone involved the information they require whilst significantly reducing person-hours required to perform this work.I want to be able to take this prototype to potential external clients to gauge their interest in developing a full version of the product that you’ve worked on.Two years ago we ran the ‘Windiness Dashboard’ project, which was a success for us and I hope we can have similar results with this project (https://www.lr.org/en-gb/latest-news/wind-chasers-fly-high-new-initiative-with-the-university-of-bristol/).

## Solution
- A web-based interface
- Inputs would consist of a live stream of data from a floating LiDAR and amast (we can provide simple examples of these). 
- Calculations required: 
   - simple standard calculations
   - Rˆ2 correlations
   - bin filling calculations
- Outputs: this is the interesting part, we need to take into account the requirements of different stakeholders which would include: us (the technical advisor and independent assessor of the KPIs), the wind farm developer (who wants the validation completed as quickly as possible but is typically not very technical), the floating LiDAR manufacturer (who is very technical and wants to know how well their device is performing) and investors (who want to know the right boxes have been ticked but have little to no interest in how any of this is achieved). When we get started I’ve got a flow diagram of how I think the data flows that I can share with you.How you suggest we balance the outputs for each of these stakeholders is something I’ll be very interested in.

## Stakeholders
- Lloyd's
- technical advisor
- independent assessor of the KPIs
- wind farm developer
  who wants the validation completed as quickly as possible but is typically not very technical
- floating LiDAR manufacturer
  who is very technical and wants to know how well their device is performing
- investors
  who want to know the right boxes have been ticked but have little to no interest in how any of this is achieved

## User Stories 
- Wind Farm Developers 
   - I want to see an overview of the KPIs, so that I can quickly see if the device is performing correctly. 
   - I want to see how close the validation is to completion, so that I can estimate when the device will be available for use. 
   - I want to be able to look into individual KPIs, so that I can see what is wrong when the KPIs aren’t met. 
- Floating LiDAR Manufacturers 
   - I want to see an overview of the KPIs, so that I can quickly see areas where the device needs to be improved or repaired. 
   - I want a detailed view of the data, so that I can correlate incorrect readings with any environmental factors which may have affected the device. 
   - I want to be able to export the data in an easily readable format, so that I can process it using other software. 
- Lloyds Registry
   - I want to be able to export the KPI data easily, so that I can add it to reports. 
   - I want to be able to connect the software to the device as directly as possible, so that my workload is reduced. 
- Technical Advisor 
   - I want to see an overview of the KPIs, so that I can advise on whether the device is reliable enough for use. 
   - I want to be able to look into individual KPIs, so that I can advise on the risks of using a potentially faulty device. 

## Requirements
In terms of measuring success, I would suggest that it needs to as a minimum: 1. Get the right answer; 2.Save us time; 3. Be intuitive to use.Constraints: I’m not aware of any constraints at this point. In terms of what tools you use to solve this problem that’s completely up to you.

IP: Happy for the solutions to be open-source.
