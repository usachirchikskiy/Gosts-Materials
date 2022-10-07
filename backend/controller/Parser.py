# -*- coding: utf-8 -*-
from bs4 import BeautifulSoup
import requests
import json
import os.path

class Parser(object):

    def __init__(self):
        self.session = requests.Session()
        self.gosts = []
        self.materials = []
        self.getGostsFile()
        self.getMaterialsFile()

    def getGostsFile(self):
        f = open('data/gosts.json')
        self.gosts = json.load(f)
        f.close()

    def getMaterialsFile(self):
        f = open('data/materials.json')
        self.materials = json.load(f)
        f.close()

    def searchMark(self,mark):
        searchResult = []
        for obj in self.materials:
            for item in obj['items']:
                for itemInner in item['items']:
                    if mark.lower() in itemInner['material'].lower():
                        searchResult.append(itemInner)
        return searchResult

    def searchAdvanced(self,dict):
        url = "http://www.splav-kharkov.com/quest_rez.php"
        page = self.session.post(url, data=dict).content
        soup = BeautifulSoup(page,"lxml")
        allItems = []
        groupTitle = soup.find_all('table', {'width':'730',"cellpadding":"2",'align': 'center'})
        groupItems = soup.find_all('table', {'rules':'none','width':'730','align': 'center'})
        # print("LenTitle",len(groupTitle))
        # print("LenItems",len(groupItems))
        for i in range(0,len(groupTitle)):
            object = {}
            object['name'] = groupTitle[i].get_text().replace(" \xa0 ","").strip()
            objectItems = []
            for j in groupItems[i].find_all("td",{"width":"25%"}):
                objectItemName = j.get_text().replace(" \xa0 ","").strip()
                objectItems.append(objectItemName)
            object['items'] = objectItems
            allItems.append(object)
        return allItems

    def downloadFile(self,url):
        if not os.path.isfile("files/"+url.split("/")[-1]):
            r = self.session.get(url, stream=True)
            with open("files/"+url.split("/")[-1], 'wb') as fd:
                for chunk in r.iter_content(1024):
                    fd.write(chunk)
        return "files/"+url.split("/")[-1]


    #---------------------
    # TODO: ссылки для скачиваний
    # self.urlGosts = "http://www.ukrtop.info/gost/index.php"
    # self.urlMaterials = "http://www.splav-kharkov.com/choose_type.php"

    #---------------------
    # TODO: Парсинг материалов
    # def getMaterials(self):
    #     page = self.session.get(self.urlMaterials).content
    #     soup = BeautifulSoup(page,'lxml')
    #     id = 1
    #     allItems = []
    #     row = {}
    #     cnt = 0
    #     records = soup.find_all('table', {'rules':'none','align': 'center'})
    #     for record in records:
    #         if(cnt%2==0):
    #             row['id'] = id
    #             row['name'] = record.get_text().replace(" \xa0 ","").strip()
    #             id =id + 1
    #         else:
    #             lis = record.find_all('td')
    #             listOfItems = []
    #             itemId = 1
    #             for li in lis:
    #                 itemRow = {}
    #                 itemRow['id'] = itemId
    #                 itemRow['name'] = li.get_text().replace(" \xa0 ","").strip()
    #                 url = "http://www.splav-kharkov.com/" + li.find('a').get('href')
    #                 itemRow['items']=self.getInfo(url)
    #                 itemId = itemId + 1
    #                 listOfItems.append(itemRow)
    #             row['items'] = listOfItems
    #             allItems.append(row)
    #             row = {}
    #         cnt = cnt + 1
    #
    #     self.writeFile(allItems)

    # def getInfo(self,url):
    #     print("URL",url)
    #     listOfItems = []
    #     page = self.session.get(url).content
    #     soup = BeautifulSoup(page,'lxml')
    #     records = soup.find('table', {'cellpadding':'6','align': 'center'}).find_all('tr')[1:]
    #     id = 1
    #     for record in records:
    #         url = "http://www.splav-kharkov.com/" + record.find('td').find('a').get("href")
    #         item = {}
    #         item['id'] = id
    #         item['material'] = record.find('td').get_text().replace(" \xa0 ","").strip()
    #         item['description'] = record.find_all('td')[-1].get_text().replace(" \xa0 ","").strip()
    #         self.getChemicalCompound(url,item)
    #         id = id + 1
    #         listOfItems.append(item)
    #     return listOfItems

    # def getChemicalCompound(self,url,item):
    #     page = self.session.get(url).content
    #     soup = BeautifulSoup(page,'lxml')
    #     chemical = ""
    #     try:
    #         recordsTop = soup.find('table',{'width':'728'}).find('tr', {"align": "center"}).find_all("td")
    #         recordsDown = soup.find('table',{'width':'728'}).find_all('tr', {"align": "center"})[1].find_all("td")
    #         for i in range(0,len(recordsTop)):
    #             chemical = chemical + recordsTop[i].get_text().replace(" \xa0 ","") + " " + recordsDown[i].get_text().replace(" \xa0 ","").split(" ")[0] +" "+ recordsDown[i].get_text().replace(" \xa0 ","").split(" ")[-1] + "\n"
    #         item['chemical'] = chemical.strip()
    #     except Exception as e:
    #         item['chemical'] = "В последней версии ГОСТа материал отсутствует"

    #---------------------
    # TODO: Парсинг Гостов
    # def getGosts(self):
    #     page = self.session.get(self.urlGosts).text
    #     soup = BeautifulSoup(page,'lxml')
    #     records = soup.find('table', {'align': 'center'}).find_all('td')
    #     id = 1
    #     itemRowId = 1
    #     allItems = []
    #     row = {}
    #     listOfItemsRow = []
    #     for record in records:
    #         if record.get('align') == 'center':
    #             checkIsEmpty = not bool(row)
    #             if checkIsEmpty :
    #                 row['id'] = id
    #                 row['name'] = record.get_text().replace(" \xa0 ","")
    #                 id =id + 1
    #             else:
    #                 row['items'] = listOfItemsRow
    #                 allItems.append(row)
    #                 listOfItemsRow = []
    #                 row = {}
    #                 itemRowId = 1
    #         else:
    #             itemOfRow = {}
    #             itemOfRow['id'] = itemRowId
    #             itemOfRow['name'] = record.get_text().replace(" \xa0 ","")
    #             url = "http://www.ukrtop.info/gost/" + record.find('a').get("href")
    #             itemOfRow['items'] = self.getItemOfRowGosts(itemOfRow,url)
    #             listOfItemsRow.append(itemOfRow)
    #             itemRowId = itemRowId + 1
    #     self.writeFile(allItems)

    # def getItemOfRowGosts(self,item,url):
    #     listOfItemsRow = []
    #     page = self.session.get(url).text
    #     soup = BeautifulSoup(page,'html.parser')
    #     records = soup.find('table', {'align': 'center'}).find_all('td')
    #     cnt = 0
    #     itemId = 1
    #     item = {}
    #     for record in records:
    #         if cnt%2 == 0:
    #             item["id"] = itemId
    #             item['name'] = record.get_text().replace(" \xa0 ","")
    #             url = "http://www.ukrtop.info/gost/" + record.find('a').get('href')
    #             item['downloadlink'] = self.getDownloadLink(url)
    #             itemId = itemId + 1
    #         else:
    #             item['description'] = record.get_text()
    #             listOfItemsRow.append(item)
    #             item = {}
    #         cnt = cnt + 1
    #     return listOfItemsRow

    # def getDownloadLink(self,url):
    #     page = self.session.get(url).text
    #     soup = BeautifulSoup(page,'html.parser')
    #     downloadlink = "http://www.ukrtop.info/gost/" + soup.find('table', {'align': 'center'}).find('td', {'align': 'center'}).find("a").get('href')
    #     return downloadlink

    #---------------------
    # TODO: запись в файл
    # def writeFile(self,list):
    #     with open('UpdatedMaterials.json', 'w') as fout:
    #         json.dump(list, fout, indent=4)
