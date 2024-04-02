package com.example.idsdemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName AbDetails
 * @Description TODO
 * @Author YU
 * @Date 2023/11/29 13:32
 * @Version 1.0
 **/
@Data
@TableName("abnormal_details")
public class AbDetails {

        @TableField("ab_id")
        private String abId;

        @TableField("`Dst Port`")
        private String dstPort;

        @TableField("Protocol")
        private String protocol;

        @TableField("Timestamp")
        private String timestamp;

        @TableField("`Flow Duration`")
        private String flowDuration;

        @TableField("`Tot Fwd Pkts`")
        private String totFwdPkts;

        @TableField("`Tot Bwd Pkts`")
        private String totBwdPkts;

        @TableField("`TotLen Fwd Pkts`")
        private String totLenFwdPkts;

        @TableField("`TotLen Bwd Pkts`")
        private String totLenBwdPkts;

        @TableField("`Fwd Pkt Len Max`")
        private String fwdPktLenMax;

        @TableField("`Fwd Pkt Len Min`")
        private String fwdPktLenMin;

        @TableField("`Fwd Pkt Len Mean`")
        private String fwdPktLenMean;

        @TableField("`Fwd Pkt Len Std`")
        private String fwdPktLenStd;

        @TableField("`Bwd Pkt Len Max`")
        private String bwdPktLenMax;

        @TableField("`Bwd Pkt Len Min`")
        private String bwdPktLenMin;

        @TableField("`Bwd Pkt Len Mean`")
        private String bwdPktLenMean;

        @TableField("`Bwd Pkt Len Std`")
        private String bwdPktLenStd;

        @TableField("`Flow Byts/s`")
        private String flowBytsPerSec;

        @TableField("`Flow Pkts/s`")
        private String flowPktsPerSec;

        @TableField("`Flow IAT Mean`")
        private String flowIatMean;

        @TableField("`Flow IAT Std`")
        private String flowIatStd;

        @TableField("`Flow IAT Max`")
        private String flowIatMax;

        @TableField("`Flow IAT Min`")
        private String flowIatMin;

        @TableField("`Fwd IAT Tot`")
        private String fwdIatTot;

        @TableField("`Fwd IAT Mean`")
        private String fwdIatMean;

        @TableField("`Fwd IAT Std`")
        private String fwdIatStd;

        @TableField("`Fwd IAT Max`")
        private String fwdIatMax;

        @TableField("`Fwd IAT Min`")
        private String fwdIatMin;

        @TableField("`Bwd IAT Tot`")
        private String bwdIatTot;

        @TableField("`Bwd IAT Mean`")
        private String bwdIatMean;

        @TableField("`Bwd IAT Std`")
        private String bwdIatStd;

        @TableField("`Bwd IAT Max`")
        private String bwdIatMax;

        @TableField("`Bwd IAT Min`")
        private String bwdIatMin;

        @TableField("`Fwd PSH Flags`")
        private String fwdPshFlags;

        @TableField("`Bwd PSH Flags`")
        private String bwdPshFlags;

        @TableField("`Fwd URG Flags`")
        private String fwdUrgFlags;

        @TableField("`Bwd URG Flags`")
        private String bwdUrgFlags;

        @TableField("`Fwd Header Len`")
        private String fwdHeaderLen;

        @TableField("`Bwd Header Len`")
        private String bwdHeaderLen;

        @TableField("`Fwd Pkts/s`")
        private String fwdPktsPerSec;

        @TableField("`Bwd Pkts/s`")
        private String bwdPktsPerSec;

        @TableField("`Pkt Len Min`")
        private String pktLenMin;

        @TableField("`Pkt Len Max`")
        private String pktLenMax;

        @TableField("`Pkt Len Mean`")
        private String pktLenMean;

        @TableField("`Pkt Len Std`")
        private String pktLenStd;

        @TableField("`Pkt Len Var`")
        private String pktLenVar;

        @TableField("`FIN Flag Cnt`")
        private String finFlagCnt;

        @TableField("`SYN Flag Cnt`")
        private String synFlagCnt;

        @TableField("`RST Flag Cnt`")
        private String rstFlagCnt;

        @TableField("`PSH Flag Cnt`")
        private String pshFlagCnt;

        @TableField("`ACK Flag Cnt`")
        private String ackFlagCnt;

        @TableField("`URG Flag Cnt`")
        private String urgFlagCnt;

        @TableField("`CWE Flag Count`")
        private String cweFlagCount;

        @TableField("`ECE Flag Cnt`")
        private String eceFlagCnt;

        @TableField("`Down/Up Ratio`")
        private String downUpRatio;

        @TableField("`Pkt Size Avg`")
        private String pktSizeAvg;

        @TableField("`Fwd Seg Size Avg`")
        private String fwdSegSizeAvg;

        @TableField("`Bwd Seg Size Avg`")
        private String bwdSegSizeAvg;

        @TableField("`Fwd Byts/b Avg`")
        private String fwdBytsBAvg;

        @TableField("`Fwd Pkts/b Avg`")
        private String fwdPktsBAvg;

        @TableField("`Fwd Blk Rate Avg`")
        private String fwdBlkRateAvg;

        @TableField("`Bwd Byts/b Avg`")
        private String bwdBytsBAvg;

        @TableField("`Bwd Pkts/b Avg`")
        private String bwdPktsBAvg;

        @TableField("`Bwd Blk Rate Avg`")
        private String bwdBlkRateAvg;

        @TableField("`Subflow Fwd Pkts`")
        private String subflowFwdPkts;

        @TableField("`Subflow Fwd Byts`")
        private String subflowFwdByts;

        @TableField("`Subflow Bwd Pkts`")
        private String subflowBwdPkts;

        @TableField("`Subflow Bwd Byts`")
        private String subflowBwdByts;

        @TableField("`Init Fwd Win Byts`")
        private String initFwdWinByts;

        @TableField("`Init Bwd Win Byts`")
        private String initBwdWinByts;

        @TableField("`Fwd Act Data Pkts`")
        private String fwdActDataPkts;

        @TableField("`Fwd Seg Size Min`")
        private String fwdSegSizeMin;

        @TableField("`Active Mean`")
        private String activeMean;

        @TableField("`Active Std`")
        private String activeStd;

        @TableField("`Active Max`")
        private String activeMax;

        @TableField("`Active Min`")
        private String activeMin;

        @TableField("`Idle Mean`")
        private String idleMean;

        @TableField("`Idle Std`")
        private String idleStd;

        @TableField("`Idle Max`")
        private String idleMax;

        @TableField("`Idle Min`")
        private String idleMin;

        @TableField("`Label`")
        private String label;
}

