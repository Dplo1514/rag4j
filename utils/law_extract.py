from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import time

# WebDriver 설정
options = webdriver.ChromeOptions()
service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service, options=options)
wait = WebDriverWait(driver, 30)

try:
    # 첫 번째 페이지 접속
    BASE_URL = "https://glaw.scourt.go.kr/wsjo/trty/sjo600.do"
    driver.get(BASE_URL)
    wait.until(lambda d: d.execute_script("return document.readyState") == "complete")

    page_number = 1  # 페이지 번호 추적

    while True:
        print(f"현재 페이지: {page_number}")

        # 현재 페이지의 a/strong 태그를 가져옴
        try:
            strong_elements = wait.until(EC.presence_of_all_elements_located(
                (By.XPATH, "/html/body/div[1]/div[4]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/a/strong")
            ))
        except Exception as e:
            print(f"tbody 안에 strong 태그를 가져오는 데 실패했습니다: {e}")
            break

        # 현재 페이지의 모든 파일 다운로드 처리
        for index, strong in enumerate(strong_elements):
            try:
                # 새로고침된 DOM에서 strong 요소 다시 가져오기
                strong_elements = wait.until(EC.presence_of_all_elements_located(
                    (By.XPATH, "/html/body/div[1]/div[4]/div/div/div[2]/div[2]/table/tbody/tr/td[2]/a/strong")
                ))
                strong = strong_elements[index]

                driver.execute_script("arguments[0].scrollIntoView(true);", strong)
                strong.click()
                print(f"{index + 1}번째 strong 클릭 완료")

                # 새 창으로 전환
                driver.switch_to.window(driver.window_handles[-1])
                print(f"새 창으로 전환: {driver.current_url}")

                # 첫 번째 a 태그 클릭
                first_a = wait.until(EC.element_to_be_clickable(
                    (By.XPATH, "/html/body/div[1]/div[3]/div[2]/div[1]/div/ul/li[2]/a")
                ))
                first_a.click()
                print("첫 번째 링크 클릭 완료")

                # 두 번째 새 창으로 전환
                driver.switch_to.window(driver.window_handles[-1])
                print(f"두 번째 창으로 전환: {driver.current_url}")

                # 두 번째 창에서 조건 처리 및 span 클릭
                try:
                    # label[2]가 존재하는지 확인
                    label_exists = driver.find_elements(By.XPATH, "/html/body/div[1]/div[1]/div[1]/label[2]")
                    if label_exists:
                        print("label[2] 존재 확인, input[2] 클릭 중...")
                        input_element = wait.until(EC.element_to_be_clickable(
                            (By.XPATH, "/html/body/div[1]/div[1]/div[1]/input[2]")
                        ))
                        input_element.click()
                        print("input[2] 클릭 완료")

                    # span[1] 클릭
                    second_span = wait.until(EC.element_to_be_clickable(
                        (By.XPATH, "/html/body/div[1]/div[2]/a[1]/span[1]")
                    ))
                    second_span.click()
                    print("span[1] 클릭 완료")
                except Exception as e:
                    print(f"조건 처리 중 오류 발생: {e}")

                # 새 창 닫기
                driver.close()
                print("새로운 창 닫기 완료")

            except Exception as e:
                print(f"오류 발생: {e}")

            finally:
                # 창 개수 확인 후 남아 있는 새 창 닫기
                while len(driver.window_handles) > 1:
                    driver.switch_to.window(driver.window_handles[-1])
                    driver.close()
                    print("잔여 새 창 닫기 완료")

                # 첫 번째 창으로 전환
                driver.switch_to.window(driver.window_handles[0])
                print("첫 번째 창으로 복귀 완료")

        # 한 페이지의 모든 파일 다운로드 후, 다음 페이지로 이동
        try:
            page_input = wait.until(EC.presence_of_element_located(
                (By.XPATH, "/html/body/div[1]/div[4]/div/div/div[1]/div[2]/div/fieldset/input")
            ))
            page_input.clear()  # 기존 값 제거
            page_input.send_keys(str(page_number + 1))  # 다음 페이지 번호 입력
            print(f"다음 페이지 번호 입력: {page_number + 1}")

            next_button = wait.until(EC.element_to_be_clickable(
                (By.XPATH, "/html/body/div[1]/div[4]/div/div/div[1]/div[2]/div/fieldset/a/img")
            ))
            driver.execute_script("arguments[0].scrollIntoView(true);", next_button)
            next_button.click()  # 이동 버튼 클릭
            print("다음 페이지로 이동 중...")

            # 페이지 전환 대기
            wait.until(lambda d: d.execute_script("return document.readyState") == "complete")
            time.sleep(2)  # 추가 대기
            page_number += 1  # 페이지 번호 증가
        except Exception as e:
            print("더 이상 다음 페이지가 없습니다. 종료합니다.")
            break

finally:
    # 브라우저 종료
    driver.quit()
    print("브라우저 종료 완료")
