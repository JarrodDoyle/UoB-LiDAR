import glob

fileNames = glob.glob("unprocessedData/*.csv")

for name in fileNames:
    csv = open(name, "r")
    lines = csv.readlines()
    headers = lines[:3]

    part = 1
    date = ""
    
    for line in lines[3:]:
        if date != line[:10]:
            date = line[:10]

            csvOut = open(name[2:-4] + "_" + date + ".csv", "w")
            for header in headers:
                csvOut.write(header)

            part += 1

        csvOut.write(line)
